import List from './List'
import type { Aula, Estacionamiento } from '../types/types'
import { Link } from 'react-router-dom'

export default function Dependencias ({ dependencias, nombreDependencia }: { dependencias: Aula[] | Estacionamiento[], nombreDependencia: string }) {
  return (
    <List array={dependencias} itemName={nombreDependencia} dependencia>
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
  )
}
