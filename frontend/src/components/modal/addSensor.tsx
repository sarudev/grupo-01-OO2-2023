import { ILugarTipo, Routes } from '../../types/enums'
import { type Lugares } from '../../types/types'
import { useAppDispatch } from '../../hooks/Redux.'
import { setSensores } from '../../redux/reducer/sensores'
import { closeModal } from '../../redux/reducer/modal'
import { apiUrl, firstUpper } from '../../utils/utils'
import useErrorMessage from '../../hooks/useErrorMessage'
import axios from 'axios'
import '../../styles/modalcontent.scss'
import { useState, useEffect } from 'react'

export default function AddLugar ({ lugar }: { lugar: Lugares }) {
  const [sensoresTipos, setSensoresTipos] = useState([])
  const { Message, setError } = useErrorMessage()
  const dispatch = useAppDispatch()

  useEffect(() => {
    let url = Routes.BaseUrl as string

    if (lugar.tipo === ILugarTipo.Aula || lugar.tipo === ILugarTipo.Estacionamiento) url += `/${lugar.lugar.tipo}/${lugar.lugar.id}/${lugar.tipo}`
    else url += `/${lugar.tipo}`
    const sensTipos = async () => {
      const { data } = await axios.get(url + '/sensor', { withCredentials: true })
      setSensoresTipos(data)
    }
    void sensTipos()
  }, [])

  const handleCreate: React.FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault()
    const form = e.target as HTMLFormElement
    const input = form[0] as HTMLInputElement
    const sensorTipo = input.value

    const url = apiUrl(lugar)

    async function request () {
      try {
        await axios.post(url + '/sensor', { sensorTipo }, { withCredentials: true })

        const { data } = await axios.get(url, { withCredentials: true })

        const sensores = data.sensores
        dispatch(setSensores(sensores))
        dispatch(closeModal())
      } catch (e: any) {
        console.log(e)
        setError(`Ya existe un sensor ${sensorTipo} para este ${lugar.tipo}`)
      }
    }

    void request()
  }

  return (
    <form onSubmit={handleCreate}>
      <div>
        <span>Crear Sensor</span>
      </div>
      <div className="inputs">
        <div className="container-input">
        <select className="input">
          {sensoresTipos.map(s => <option key={firstUpper(s)} value={s}>{firstUpper(s)}</option>)}
        </select>
        </div>
      </div>
      <button className="send" type="submit">Crear Sensor</button>
      <Message />
    </form>
  )
}
