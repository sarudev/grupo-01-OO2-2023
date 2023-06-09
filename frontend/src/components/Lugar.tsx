import { useState, useMemo } from 'react'
import Icon from './Icon'
import { type SensorType, lugarDependencia } from '../types/enums'
import Tabs from './Tabs'
import AddButton from './AddButton'
import Modal from './Modal'
import type { Aula, Lugar as ILugar, Sensor } from '../types/types'
import { firstUpper } from '../utils/utils'
import Dependencias from './Dependencias'
import Sensores from './Sensores'
import Historial from './Historial'
import AddLugar from './modal/AddLugar'
import AddSensor from './modal/AddSensor'
import campus from '../assets/campus'
import { Link, useParams } from 'react-router-dom'
import '../styles/lugar.scss'

function useVariables (lugar: ILugar, currentTab: string) {
  const tabs = useMemo(() => {
    const tabs = ['sensores', 'historial']
    const ld = lugarDependencia[lugar.tipo]
    const dependencia = ld != null && ld.length > 0 ? [ld, ...tabs] : null

    return dependencia ?? tabs
  }, [])

  const isDependencia = useMemo(() => currentTab != null && currentTab === lugarDependencia[lugar.tipo], [currentTab])
  const isSensor = useMemo(() => currentTab === 'sensores', [currentTab])
  const isHistorial = useMemo(() => currentTab === 'historial', [currentTab])

  const dependencia = useMemo(() => {
    if ('aulas' in lugar) return lugar.aulas
    if ('estacionamientos' in lugar) return lugar.estacionamientos
    return null
  }, [])

  return {
    dependencia,
    tabs,
    isDependencia,
    isSensor,
    isHistorial
  }
}

function useModal () {
  const [showModal, setShowModal] = useState(false)
  const handleOpenModal = () => setShowModal(true)
  const handleCloseModal = () => setShowModal(false)

  return {
    showModal,
    handleOpenModal,
    handleCloseModal
  }
}

export default function Lugar ({ lugar }: { lugar: ILugar }) {
  // console.log(useParams())
  const [currentTab, setCurrentTab] = useState('')

  const { dependencia, tabs, isDependencia, isSensor, isHistorial } = useVariables(lugar, currentTab)
  const { showModal, handleOpenModal, handleCloseModal } = useModal()

  const sensorHandleToggle = (sensor: Sensor) => {
    sensor.activo = !sensor.activo
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
      lugar: null,
      nombre: input.value,
      luces: false,
      sensores: [],
      historial: []
    }

    campus.edificios.find(e => e.nombre === lugar.nombre)!.aulas.push(aula)
    handleCloseModal()
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
          <div className='content-list'>
            {isDependencia && dependencia != null && <Dependencias originalDependencias={dependencia} nombreDependencia={lugarDependencia[lugar.tipo]!} />}
            {isSensor && <Sensores originalSensores={lugar.sensores} handleToggle={sensorHandleToggle} />}
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
