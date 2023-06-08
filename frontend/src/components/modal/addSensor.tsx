import { SensorType } from '../../types/enums'
import '../../styles/modalcontent.scss'

export default function AddLugar ({ onCreate }: { onCreate: React.FormEventHandler<HTMLFormElement> }) {
  return (
    <form onSubmit={onCreate}>
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
    </form>
  )
}
