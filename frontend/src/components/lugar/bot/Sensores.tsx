import axios from 'axios'
import { useAppDispatch, useAppSelector } from '../../../hooks/Redux.'
import { type Lugares, type ISensor } from '../../../types/types'
import List from '../../List'
import { useLayoutEffect, useState } from 'react'
import { Routes, UserRole } from '../../../types/enums'
import { setSensores } from '../../../redux/reducer/sensores'
import Modal from '../../Modal'
import AddSensor from '../../modal/AddSensor'
import AddButton from '../../AddButton'
import useSelect from '../../../hooks/useSelect'
import { apiUrl } from '../../../utils/utils'

export default function Sensores ({ lugar, userRole, visible }: { lugar: Lugares, userRole: UserRole, visible: boolean }) {
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
    const url = apiUrl(lugar)

    void axios.put(Routes.BaseUrl + '/sensor', {
      sensorId: s.id,
      activo: !s.activo
    }, { withCredentials: true }).then(() => {
      void axios.get(url, { withCredentials: true }).then(({ data }: { data: Lugares }) => {
        dispatch(setSensores(data.sensores))
      }).catch(e => console.error(e))
    }).catch((err) => {
      console.error('Esto no deberia hacer sucedido...')
      console.error(err)
    })
  }

  if (!visible) return null

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
              {userRole === UserRole.Admin && <button onClick={() => handleToggle(s)} className='btn'>{s.activo ? 'Desactivar' : 'Activar'}</button>}
            </>
          )
        }}
      </List>
      {userRole === UserRole.Admin && <AddButton text='sensor' />}
      <Modal>
        <AddSensor lugar={lugar} />
      </Modal>
    </>
  )
}
