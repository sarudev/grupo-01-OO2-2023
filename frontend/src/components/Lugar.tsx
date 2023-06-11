import { useMemo, useLayoutEffect } from 'react'
import Icon from './Icon'
import Tabs from './Tabs'
import AddButton from './AddButton'
import Modal from './Modal'
import { type Lugares, type IHistorial, type ISensor, type ILugar, type Dependencia, type IAula, type IEstacionamiento, type IEdificio, type IParking } from '../types/types'
import { firstUpper } from '../utils/utils'
import Dependencias from './Dependencias'
// import Sensores from './Sensores'
import Historial from './Historial'
import AddLugar from './modal/AddLugar'
// import AddSensor from './modal/AddSensor'
// import campus from '../assets/campus'
// import { Link, useParams } from 'react-router-dom'
import '../styles/lugar.scss'
import { ILugarTipo, LugarDependencia } from '../types/enums'
import { Link } from 'react-router-dom'
import { useSelector } from 'react-redux'
import { useAppDispatch, useAppSelector } from '../hooks/Redux.'
import { setDependencias } from '../redux/reducer/dependencias'
import { setSensores } from '../redux/reducer/sensores'
import { setHistorial } from '../redux/reducer/historial'
// import { DependenciaHija } from '../types/helpers'
import axios from 'axios'
import { isEdificio, isParking } from '../types/typeguards'
import { closeModal } from '../redux/reducer/modal'

export default function Lugar <T extends Lugares> ({ lugar }: { lugar: T }) {
  const currentTab = useAppSelector(s => s.currentTab)
  const dependencias = useAppSelector(s => s.dependencias)
  const sensores = useAppSelector(s => s.sensores)
  const historial = useAppSelector(s => s.historial)
  const dispatch = useAppDispatch()

  useLayoutEffect(() => {
    const dependencias = isEdificio(lugar) ? lugar.aulas : isParking(lugar) ? lugar.estacionamientos : null
    dispatch(setDependencias(dependencias))
    dispatch(setSensores(lugar.sensores))
    dispatch(setHistorial(lugar.historial))
  }, [])

  const isDependencia = useMemo(() => currentTab != null && currentTab === LugarDependencia[lugar.tipo], [currentTab])
  const isSensor = useMemo(() => currentTab === 'sensores', [currentTab])
  const isHistorial = useMemo(() => currentTab === 'historial', [currentTab])

  // const sensorHandleToggle = (sensor: Sensor) => {
  //   sensor.activo = !sensor.activo
  // }
  const handleCreateDependencia = () => {
    const lugarTipo = lugar.tipo === ILugarTipo.Edificio ? ILugarTipo.Edificio : ILugarTipo.Parking
    void axios.get(`http://localhost:5282/${lugarTipo}/${lugar.nombre}`)
      .then(({ data }: { data: IEdificio | IParking }) => {
        const dependencias = isEdificio(data) ? data.aulas : isParking(data) ? data.estacionamientos : null
        dispatch(setDependencias(dependencias))
        dispatch(closeModal())
      })
  }
  // const handleCreateSensor: React.FormEventHandler<HTMLFormElement> = (e) => {
  //   e.preventDefault()
  //   const form = e.target as HTMLFormElement

  //   const input = form[0] as HTMLInputElement & { value: SensorType }

  //   if (lugar.sensores.find(s => s.tipo === input.value) == null) {
  //     const sensor: Sensor = {
  //       activo: false,
  //       id: 0,
  //       tipo: input.value
  //     }

  //     lugar.sensores.push(sensor)
  //   }
  //   handleCloseModal()
  // }

  // mover los arrays states dentro de cada "contenido"

  return (
    <div className='container'>
      <div className="top">
        <div className="icon">
          <Icon svgName={lugar.tipo} />
        </div>
        <div className="text">
          <div className="text-container">
            <div className="title">{firstUpper(lugar.tipo)}</div>
            <div className="description">{lugar.nombre}</div>
          </div>
          <Link className="back" to={lugar.lugar == null ? '/' : `/${lugar.lugar.tipo}/${lugar.lugar.nombre}`}>Volver a {lugar?.lugar?.nombre ?? 'Campus'}</Link>
        </div>
      </div>
      <div className="bot">
        <nav>
          <Tabs tabsNames={[LugarDependencia[lugar.tipo], 'sensores', 'historial']} />
        </nav>
        <div className="content">
          <div className='content-list'>
            {isDependencia && dependencias != null && <Dependencias nombreDependencia={LugarDependencia[lugar.tipo]!} />}
            {/* {isSensor && <Sensores originalSensores={sensores} />} */}
            {isHistorial && <Historial historial={historial} />}
          </div>
          {isDependencia && <AddButton text={LugarDependencia[lugar.tipo]!.slice(0, -1)} />}
          {isSensor && <AddButton text='sensor' />}
        </div>
      </div>
      <Modal>
        <>
          {isDependencia && <AddLugar lugar={lugar} onCreate={handleCreateDependencia} />}
          {/* {isSensor && <AddSensor />} */}
        </>
      </Modal>
    </div>
  )
}
