import List from './List'
import { type Sensor, type Sensor as ISensor } from '../types/types'

export default function Sensores ({ sensores, handleToggle }: { sensores: ISensor[], handleToggle: (sensor: Sensor) => void }) {
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
            <div className='info'>
              <div className="activo" data-activo={s.activo} />
              <div className="tipo">{s.tipo}</div>
            </div>
            <button onClick={() => handleToggle(s)} className='btn'>{s.activo ? 'Desactivar' : 'Activar'}</button>
          </>
        )
      }}
    </List>
  )
}
