import { SensorType } from '../../types/enums'
import { type Lugares } from '../../types/types'
import { useAppDispatch } from '../../hooks/Redux.'
import { setSensores } from '../../redux/reducer/sensores'
import { closeModal } from '../../redux/reducer/modal'
import { apiUrl } from '../../utils/utils'
import useErrorMessage from '../../hooks/useErrorMessage'
import axios from 'axios'
import '../../styles/modalcontent.scss'

export default function AddLugar ({ lugar }: { lugar: Lugares }) {
  const { Message, setError } = useErrorMessage()
  const dispatch = useAppDispatch()

  const handleCreate: React.FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault()
    const form = e.target as HTMLFormElement
    const input = form[0] as HTMLInputElement
    const sensorType = input.value

    const url = apiUrl(lugar)
    console.log(url)
    async function request () {
      try {
        await axios.post(url + '/sensor', { sensorType }, { withCredentials: true })

        const { data } = await axios.get(url, { withCredentials: true })

        const sensores = data.sensores
        dispatch(setSensores(sensores))
        dispatch(closeModal())
      } catch (e: any) {
        console.log(e)
        setError(`Ya existe un sensor ${sensorType} para este ${lugar.tipo}`)
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
          <option value={SensorType.Bascula}>{SensorType.Bascula}</option>
          <option value={SensorType.Camara}>{SensorType.Camara}</option>
          <option value={SensorType.Humedad}>{SensorType.Humedad}</option>
          <option value={SensorType.Temperatura}>{SensorType.Temperatura}</option>
          <option value={SensorType.Tiempo}>{SensorType.Tiempo}</option>
        </select>
        </div>
      </div>
      <button className="send" type="submit">Crear Sensor</button>
      <Message />
    </form>
  )
}
