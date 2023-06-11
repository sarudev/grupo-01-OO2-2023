import { useState, useEffect } from 'react'
import List from './List'
import type { IAula, IEstacionamiento } from '../types/types'
import { Link } from 'react-router-dom'
import { useAppSelector } from '../hooks/Redux.'

export default function Dependencias ({ nombreDependencia }: { nombreDependencia: string }) {
  const dependencias = useAppSelector(s => s.dependencias)
  const [input, setInput] = useState('')
  const [sortedDependencias, setSortedDependencias] = useState<IAula[] | IEstacionamiento[]>(dependencias as IAula[] | IEstacionamiento[])

  useEffect(() => {
    if (dependencias == null) return
    const deps = dependencias.toSorted((a, b) => a.nombre.localeCompare(b.nombre)).filter(e => e.nombre.includes(input)) as IAula[] | IEstacionamiento[]
    setSortedDependencias(deps)
  }, [input, dependencias])

  return (
    <>
      <div className='filter'>
        <div className='text'>Filtrar: </div>
        <input value={input} id='filter-dependencia' type="text" placeholder='Nombre del aula' onChange={(e) => setInput(e.target.value)} />
      </div>
      <List<IAula | IEstacionamiento> array={sortedDependencias} itemName={nombreDependencia} dependencia>
        {(d) => {
          if (d == null) return <></>
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
