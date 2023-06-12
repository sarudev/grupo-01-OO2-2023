import { ILugarTipo, SensorType } from '../../types/enums'
import { useEffect, useState } from 'react'
import { type Lugares } from '../../types/types'
import axios from 'axios'
import '../../styles/modalcontent.scss'

export default function AddLugar ({ lugar, onCreate }: { lugar: Lugares, onCreate?: () => void }) {
  const [error, setError] = useState('')

  const handleCreate: React.FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault()
    const form = e.target as HTMLFormElement
    const input = form[0] as HTMLInputElement
    const sensorTipo = input.value

    let url = 'http://localhost:5282'
    if (lugar.tipo === ILugarTipo.Aula || lugar.tipo === ILugarTipo.Estacionamiento) url += `/${lugar.lugar.tipo}/${lugar.lugar.nombre.replaceAll(' ', '-')}/${lugar.tipo}/${lugar.nombre}`
    else url += `/${lugar.tipo}/${lugar.nombre.replaceAll(' ', '-')}`

    void axios.post(url, { sensorTipo }).then(() => {
      onCreate?.()
    }).catch((res) => {
      const err = JSON.parse(res.request.response).error
      setError(err)
    })
  }

  useEffect(() => {
    setTimeout(() => {
      setError('')
    }, 5000)
  }, [error])

  return (
    <form onSubmit={handleCreate}>
      <div>
        <span>Crear Sensor</span>
      </div>
      <div className="inputs">
        <div className="container-input">
        <select className="input">
          <option value={SensorType.Temperatura}>{SensorType.Temperatura}</option>
          <option value={SensorType.Bascula}>{SensorType.Bascula}</option>
          <option value={SensorType.Humedad}>{SensorType.Humedad}</option>
          <option value={SensorType.Camara}>{SensorType.Camara}</option>
          <option value={SensorType.Tiempo}>{SensorType.Tiempo}</option>
        </select>
        </div>
      </div>
      <button className="send" type="submit">Crear Sensor</button>
      {error.length > 0 && <div className='error'>{ error }</div>}
    </form>
  )
}
