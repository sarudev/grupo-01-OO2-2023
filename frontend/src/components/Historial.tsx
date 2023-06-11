import { type IHistorial } from '../types/types'
import List from './List'

export default function Historial ({ historial }: { historial: IHistorial[] }) {
  return (
    <List array={historial} itemName='historial'>
      {(h) => {
        const fecha = new Date(h.fecha)

        return (
          <>
            <div className="tipo">{h.tipo}</div>
            <div className="activo">{h.mensaje}</div>
            <div className="fecha">{fecha.getDay()}/{fecha.getMonth()}/{fecha.getFullYear()} - {fecha.getHours()}:{fecha.getMinutes()}</div>
          </>
        )
      }}
    </List>
  )
}
