import { useState } from 'react'
import List from './List'
import type { Aula, Estacionamiento, Lugar } from '../types/types'
import { Link } from 'react-router-dom'

export default function Dependencias ({ originalDependencias, nombreDependencia }: { originalDependencias: Aula[] | Estacionamiento[], nombreDependencia: string }) {
  const [sortedDependencias, setSortedDependencias] = useState(originalDependencias)

  const onInputChange = (value: string) => {
    const lugares: Lugar[] = []

    for (const lug of originalDependencias) {
      if (lug.nombre.includes(value)) lugares.push(lug)
    }

    setSortedDependencias(lugares)
  }

  return (
    <>
      <FiltroDependencia onInputChange={onInputChange} />
      <List array={sortedDependencias} itemName={nombreDependencia} dependencia>
        {(d) => {
          return (
            <>
              <div className="nombre">{d.nombre}</div>
              <Link className='link' to={`${nombreDependencia.slice(0, -1)}/${d.nombre}`}>
                Visitar
              </Link>
            </>
          )
        }}
      </List>
    </>
  )
}

function FiltroDependencia ({ onInputChange }: { onInputChange: (string: string) => void }) {
  return (
    <div className='filter'>
      <div className='text'>Filtrar: </div>
      <input id='filter-dependencia' type="text" placeholder='Nombre del aula' onChange={(e) => onInputChange(e.target.value)} />
    </div>
  )
}
