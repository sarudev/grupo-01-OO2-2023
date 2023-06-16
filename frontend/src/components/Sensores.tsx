import axios from 'axios'
import { useAppDispatch, useAppSelector } from '../hooks/Redux.'
import { type Lugares, type ISensor } from '../types/types'
import List from './List'
import { useLayoutEffect, useState } from 'react'
import { ILugarTipo } from '../types/enums'
import { setSensores } from '../redux/reducer/sensores'
import Modal from './Modal'
import AddSensor from './modal/AddSensor'
import AddButton from './AddButton'
import useSelect from '../hooks/useSelect'
const { VITE_API_URL } = import.meta.env as Record<string, string>

export default function Sensores ({ lugar }: { lugar: Lugares }) {
  const sensores = useAppSelector(s => s.sensores)
  const { Select, option } = useSelect(['Todos', 'Activo', 'Inactivo'])
  const [sortedSensores, setSortedSensores] = useState(sensores)

  const dispatch = useAppDispatch()

  useLayoutEffect(() => {
    const sens = sensores.filter(s => {
      if (option === 'Todos') return true
      if (option === 'Activo' && s.activo) return true
      if (option === 'Inactivo' && !s.activo) return true
      return false
    })

    setSortedSensores(sens)
  }, [option, sensores])

  const handleToggle = (s: ISensor) => {
    let url = `http://${VITE_API_URL}:5282`
    if (lugar.tipo === ILugarTipo.Aula || lugar.tipo === ILugarTipo.Estacionamiento) url += `/${lugar.lugar.tipo}/${lugar.lugar.nombre.replaceAll(' ', '-')}/${lugar.tipo}/${lugar.nombre}`
    else url += `/${lugar.tipo}/${lugar.nombre.replaceAll(' ', '-')}`
    url += '/sensor'

    void axios.put(url, {
      sensorId: s.id,
      activo: !s.activo
    }).then(() => {
      void axios.get(url.replace('/sensor', '')).then(({ data }: { data: Lugares }) => {
        dispatch(setSensores(data.sensores))
      })
    }).catch((err) => {
      console.error('Esto no deberia hacer sucedido...')
      console.error(err)
    })
  }

  return (
    <>
      <div className='filter sensores'>
        <div className='text'>Mostrar: </div>
        <Select />
      </div>
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
      <AddButton text='sensor' />
      <Modal>
        <AddSensor lugar={lugar} />
      </Modal>
    </>
  )
}
