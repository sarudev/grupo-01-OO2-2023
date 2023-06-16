import { useState, useCallback, useEffect, useMemo, useLayoutEffect } from 'react'
import { useAppSelector } from '../hooks/Redux.'
import useInput from '../hooks/useInput'
import useSelect from '../hooks/useSelect'
import { SensorType } from '../types/enums'
import { type Lugares } from '../types/types'
import List from './List'

export default function Historial ({ lugar }: { lugar: Lugares }) {
  const historial = useAppSelector(s => s.historial)
  const [sortedHistorial, setSortedHistorial] = useState(historial)

  const { Select, option } = useSelect(['Todos', 'Tipo del sensor', 'Descripción', 'Fecha'])
  const { Select: SelectTipo, option: optionTipo } = useSelect([SensorType.Bascula, SensorType.Camara, SensorType.Humedad, SensorType.Temperatura, SensorType.Tiempo])
  const { Input, input: inputNombre } = useInput('Descripción')

  const todayDate = useMemo(() => new Date(Date.now()).toISOString().slice(0, -8), [])
  const [dateHastaRender, setDateHastaRender] = useState(todayDate)
  const { Calendar: CalendarDesde, date: dateDesde } = useCalendar('', dateHastaRender)
  const { Calendar: CalendarHasta, date: dateHasta } = useCalendar(dateDesde === '' ? todayDate : dateDesde, todayDate)

  useEffect(() => {
    if (dateHasta !== '') setDateHastaRender(dateHasta)
  }, [dateHasta])

  useLayoutEffect(() => {
    if (option === 'Todos') setSortedHistorial(historial.toSorted((a, b) => a.descripcion.localeCompare(b.descripcion)))
    else if (option === 'Tipo del sensor') setSortedHistorial(historial.filter(el => el.sensorTipo === optionTipo))
    else if (option === 'Descripción') setSortedHistorial(historial.filter(el => el.descripcion.toLowerCase().includes(inputNombre.toLowerCase())))
    else if (option === 'Fecha') {
      // sort "historial" by "dateDesde" date and "dateHasta" date
      setSortedHistorial(historial.filter(el => el.fecha.localeCompare(dateDesde) > 0 && el.fecha.localeCompare(dateHasta) < 0))
    }
  }, [option, optionTipo, inputNombre, dateDesde, dateHasta])

  return (
    <>
      <div className={`filter historial ${option === 'Todos' ? option : ''}`}>
        <div className="filter-inside-container first">
          <div className='text'>Filtrar por:</div>
          <Select />
        </div>
        <div className='filter-inside-container second '>
          { option === 'Todos' && null }
          { option === 'Tipo del sensor' && <>Tipo del sensor: <SelectTipo /></> }
          { option === 'Descripción' && <>Descripción: <Input /></> }
          { option === 'Fecha' && <>Desde: <CalendarDesde /> Hasta: <CalendarHasta /></> }
        </div>
      </div>
      <List array={sortedHistorial} itemName='historial'>
        {(h) => {
          const fecha = new Date(h.fecha).toLocaleDateString('es-ES', { day: '2-digit', month: '2-digit', year: 'numeric', hour: '2-digit', minute: '2-digit' })

          return (
            <>
              <div className="sensor-tipo">
                <div className="tipo">{h.sensorTipo}</div>
                <div className="activo">{h.descripcion}</div>
              </div>
              <div className="fecha">{fecha.replace(', ', ' - ')}</div>
            </>
          )
        }}
      </List>
    </>
  )
}
function useCalendar (minValue: string, maxValue: string) {
  const [date, setDate] = useState('')

  const Calendar = useCallback(() => (
    <input type="datetime-local" min={minValue} max={maxValue} value={date} onChange={e => setDate(e.currentTarget.value)} className='filter-input filter-historial'/>
  ), [date, minValue, maxValue])

  return {
    Calendar,
    date
  }
}
