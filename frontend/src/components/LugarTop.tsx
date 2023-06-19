import { Link } from 'react-router-dom'
import Icon from '../components/Icon'
import { firstUpper } from '../utils/utils'
import { Routes, type UserRole } from '../types/enums'
import { type Lugares } from '../types/types'

export default function LugarTop ({ lugar, userRole }: { lugar: Lugares, userRole: UserRole }) {
  return (
    <div className="top">
      <div className="icon">
        <Icon svgName={lugar.tipo} />
      </div>
      <div className="text">
        <div className="text-container">
          <div className="title">{firstUpper(lugar.tipo)}</div>
          <div className="description">{lugar.nombre}</div>
        </div>
        <div className="session-container">
          <Link className="back" to={lugar.lugar == null ? '/' : `/${lugar.lugar.tipo}/${lugar.lugar.nombre}`}>Volver a {lugar?.lugar?.nombre ?? 'Campus'}</Link>
          <div className="session">
            <span>Sesión iniciada como {userRole}</span>
            <Link className="logout" to={Routes.Logout}>Cerrar sesión</Link>
          </div>
        </div>
      </div>
    </div>
  )
}
