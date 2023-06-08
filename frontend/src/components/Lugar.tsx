import { useState, useMemo } from 'react'
import Icon from './Icon'
import { lugarDependencia } from '../types/enums'
import Tabs from './Tabs'
import '../styles/lugar.scss'
import AddButton from './AddButton'
import Modal from './Modal'
import type { Lugar as ILugar } from '../types/types'
import { firstUpper } from '../utils/utils'
import Dependencias from './Dependencias'
import Sensores from './Sensores'
import Historial from './Historial'
import AddLugar from './modal/addLugar'
import AddSensor from './modal/addSensor'

export default function Lugar ({ lugar }: { lugar: ILugar }) {
  const [currentTab, setCurrentTab] = useState('')
  const [showModal, setShowModal] = useState(false)

  const tabs = useMemo(() => {
    const tabs = ['sensores', 'historial']
    const dependencia = lugarDependencia[lugar.tipo].length > 0 ? [lugarDependencia[lugar.tipo], ...tabs] : null

    return dependencia ?? tabs
  }, [])

  const isDependencia = useMemo(() => currentTab !== '' && currentTab === lugarDependencia[lugar.tipo], [currentTab])
  const isSensor = useMemo(() => currentTab === 'sensores', [currentTab])
  const isHistorial = useMemo(() => currentTab === 'historial', [currentTab])

  const dependencia = useMemo(() => {
    if ('aulas' in lugar) return lugar.aulas
    if ('estacionamientos' in lugar) return lugar.estacionamientos
    return null
  }, [])

  const handleOpenModal = () => setShowModal(true)
  const handleCloseModal = () => setShowModal(false)

  return (
    <div className='container'>
      <div className="top">
        <div className="icon"><Icon svgName={lugar.tipo} /></div>
        <div className="text">
          <div className="title">{firstUpper(lugar.tipo)}</div>
          <div className="description">{lugar.nombre}</div>
        </div>
      </div>
      <div className="bot">
        <nav>
          <Tabs tabsNames={tabs} onTabChange={setCurrentTab}/>
        </nav>
        <div className="content">
          <div>
            {isDependencia && dependencia != null && <Dependencias dependencias={dependencia} nombreDependencia={lugarDependencia[lugar.tipo]} />}
            {isSensor && <Sensores sensores={lugar.sensores}/>}
            {isHistorial && <Historial historial={lugar.historial} />}
          </div>
          {isDependencia && <AddButton text={lugarDependencia[lugar.tipo].slice(0, -1)} onClick={handleOpenModal} />}
          {isSensor && <AddButton text='sensor' onClick={handleOpenModal} />}
        </div>
      </div>
      <Modal closeModal={handleCloseModal} open={showModal}>
        <>
          {isDependencia && <AddLugar />}
          {isSensor && <AddSensor />}
        </>
      </Modal>
    </div>
  )
}
