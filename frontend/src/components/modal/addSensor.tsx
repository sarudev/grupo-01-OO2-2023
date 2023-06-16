import { SensorType } from '../../types/enums'
import { type Lugares } from '../../types/types'
import axios from 'axios'
import { useAppDispatch } from '../../hooks/Redux.'
import { setSensores } from '../../redux/reducer/sensores'
import { closeModal } from '../../redux/reducer/modal'
import { apiUrl } from '../../utils/utils'
import '../../styles/modalcontent.scss'
import useErrorMessage from '../../hooks/useErrorMessage'

export default function AddLugar ({ lugar }: { lugar: Lugares }) {
  const { Message, setError } = useErrorMessage()
  const dispatch = useAppDispatch()

  const handleCreate: React.FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault()
    const form = e.target as HTMLFormElement
    const input = form[0] as HTMLInputElement
    const sensorTipo = input.value

    const url = apiUrl(lugar)

    async function request () {
      try {
        await axios.post(url, { sensorTipo })

        const { data } = await axios.get(url)

        const sensores = data.sensores
        dispatch(setSensores(sensores))
        dispatch(closeModal())
      } catch (e: any) {
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
