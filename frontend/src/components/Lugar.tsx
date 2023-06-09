import { useState, useMemo } from 'react'
import Icon from './Icon'
import { type SensorType, lugarDependencia } from '../types/enums'
import Tabs from './Tabs'
import AddButton from './AddButton'
import Modal from './Modal'
import type { Aula, Estacionamiento, Lugar as ILugar, Sensor } from '../types/types'
import { firstUpper } from '../utils/utils'
import Dependencias from './Dependencias'
import Sensores from './Sensores'
import Historial from './Historial'
import AddLugar from './modal/AddLugar'
import AddSensor from './modal/AddSensor'
import campus from '../assets/campus'
import useForceReRender from '../hooks/useForceReRender'
import { Link } from 'react-router-dom'
import '../styles/lugar.scss'

export default function Lugar ({ lugar }: { lugar: ILugar & { lugar: ILugar | null } }) {
  const dependencia = useMemo(() => {
    if ('aulas' in lugar) return lugar.aulas
    if ('estacionamientos' in lugar) return lugar.estacionamientos
    return null
  }, [])

  const [currentTab, setCurrentTab] = useState('')
  const [showModal, setShowModal] = useState(false)
  const [sortedSensores, setSortedSensores] = useState(lugar.sensores)
  const [sortedDependencias, setSortedDependencias] = useState(dependencia)
  const forceReRender = useForceReRender()

  const tabs = useMemo(() => {
    const tabs = ['sensores', 'historial']
    const ld = lugarDependencia[lugar.tipo]
    const dependencia = ld != null && ld.length > 0 ? [ld, ...tabs] : null

    return dependencia ?? tabs
  }, [])

  const isDependencia = useMemo(() => currentTab != null && currentTab === lugarDependencia[lugar.tipo], [currentTab])
  const isSensor = useMemo(() => currentTab === 'sensores', [currentTab])
  const isHistorial = useMemo(() => currentTab === 'historial', [currentTab])

  const handleOpenModal = () => setShowModal(true)
  const handleCloseModal = () => setShowModal(false)
  const sensorHandleToggle = (sensor: Sensor) => {
    sensor.activo = !sensor.activo
    onOptionChange(document.querySelector('select#filter-sensor')!.value)
  }
  // SOLO FUNCIONA PARA CREAR AULAS
  // SE DEBE ADAPTAR PARA FUNCIONAR
  // CON PARKINGS
  const handleCreateDependencia: React.FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault()
    const form = e.target as HTMLFormElement

    const input = form[0] as HTMLInputElement

    const aula: Aula = {
      id: 0,
      tipo: lugarDependencia[lugar.tipo]!.slice(0, -1) as 'aula' | 'estacionamiento',
      nombre: input.value,
      luces: false,
      sensores: [],
      historial: []
    }

    campus.edificios.find(e => e.nombre === lugar.nombre)!.aulas.push(aula)
    handleCloseModal()
    onInputChange('')
  }
  const handleCreateSensor: React.FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault()
    const form = e.target as HTMLFormElement

    const input = form[0] as HTMLInputElement & { value: SensorType }

    if (lugar.sensores.find(s => s.tipo === input.value) == null) {
      const sensor: Sensor = {
        activo: false,
        id: 0,
        tipo: input.value
      }

      lugar.sensores.push(sensor)
    }
    handleCloseModal()
  }

  const onOptionChange = (value: string) => {
    const sensores: Sensor[] = []

    for (const sensor of lugar.sensores) {
      if (value === 'todos') sensores.push(sensor)
      if (value === 'activo' && sensor.activo) sensores.push(sensor)
      if (value === 'inactivo' && !sensor.activo) sensores.push(sensor)
    }

    setSortedSensores(sensores)
  }
  const onInputChange = (value: string) => {
    const lugares: ILugar[] = []

    const dep = lugarDependencia[lugar.tipo] as 'aulas' | 'estacionamientos'

    const lugArr = lugar as ILugar & { lugar: ILugar | null } & { aulas: Aula[], estacionamientos: Estacionamiento[] }

    for (const lug of lugArr[dep]) {
      if (lug.nombre.includes(value)) lugares.push(lug)
    }

    setSortedDependencias(lugares)
  }

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
          <Tabs tabsNames={tabs} onTabChange={setCurrentTab}/>
        </nav>
        <div className="content">
          {isDependencia && <FiltroDependencia onInputChange={onInputChange} />}
          {isSensor && <FiltroSensor onOptionChange={onOptionChange}/>}
          {isHistorial && null}
          <div className='content-list'>
            {isDependencia && sortedDependencias != null && <Dependencias dependencias={sortedDependencias} nombreDependencia={lugarDependencia[lugar.tipo]!} />}
            {isSensor && <Sensores sensores={sortedSensores} handleToggle={sensorHandleToggle} />}
            {isHistorial && <Historial historial={lugar.historial} />}
          </div>
          {isDependencia && <AddButton text={lugarDependencia[lugar.tipo]!.slice(0, -1)} onClick={handleOpenModal} />}
          {isSensor && <AddButton text='sensor' onClick={handleOpenModal} />}
        </div>
      </div>
      <Modal closeModal={handleCloseModal} open={showModal}>
        <>
          {isDependencia && <AddLugar dependencia={lugarDependencia[lugar.tipo]!.slice(0, -1)!} onCreate={handleCreateDependencia} />}
          {isSensor && <AddSensor onCreate={handleCreateSensor} />}
        </>
      </Modal>
    </div>
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
