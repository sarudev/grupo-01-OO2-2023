import List from './List'
import { type Sensor, type Sensor as ISensor } from '../types/types'
import { useState } from 'react'

export default function Sensores ({ originalSensores, handleToggle }: { originalSensores: ISensor[], handleToggle: (sensor: Sensor) => void }) {
  const [sortedSensores, setSortedSensores] = useState(originalSensores)

  const onOptionChange = (value: string) => {
    const sensores: Sensor[] = []

    for (const sensor of originalSensores) {
      if (value === 'todos') sensores.push(sensor)
      if (value === 'activo' && sensor.activo) sensores.push(sensor)
      if (value === 'inactivo' && !sensor.activo) sensores.push(sensor)
    }

    setSortedSensores(sensores)
  }

  return (
    <>
      <FiltroSensor onOptionChange={onOptionChange}/>
      <List array={sortedSensores} itemName='sensores'>
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
    </>
  )
}

function FiltroSensor ({ onOptionChange }: { onOptionChange: (string: string) => void }) {
  return (
    <div className='filter'>
      <div className='text'>Mostrar: </div>
      <select onChange={(e) => onOptionChange(e.target.value)} id="filter-sensor">
        <option value="todos">Todos</option>
        <option value="activo">Activo</option>
        <option value="inactivo">Inactivo</option>
      </select>
    </div>
  )
}
