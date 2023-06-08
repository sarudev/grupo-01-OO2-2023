import List from './List'
import { type Sensor as ISensor } from '../types/types'

export default function Sensores ({ sensores }: { sensores: ISensor[] }) {
  if (sensores.length < 1) {
    return (
      <div className='list empty'>No hay sensores para mostrar.</div>
    )
  }

  return (
    <List array={sensores} itemName='sensores'>
      {(s) => {
        return (
          <>
            <div className="activo" data-activo={s.activo} />
            <div className="tipo">{s.tipo}</div>
          </>
        )
      }}
    </List>
  )
}
