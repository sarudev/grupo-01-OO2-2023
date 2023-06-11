import { firstUpper } from '../../utils/utils'
import { useRef, useEffect } from 'react'
import { type Lugares } from '../../types/types'
import axios from 'axios'
import '../../styles/modalcontent.scss'
import { ILugarTipo, LugarDependencia } from '../../types/enums'

export default function AddLugar ({ lugar, onCreate }: { lugar: Lugares, onCreate?: () => void }) {
  const inputRef = useRef<HTMLInputElement>(null)

  useEffect(() => {
    inputRef.current!.focus()
  }, [])

  const handleCreate: React.FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault()
    const form = e.target as HTMLFormElement
    const input = form[0] as HTMLInputElement
    const dependenciaName = input.value

    const lugarTipo = lugar.tipo === ILugarTipo.Edificio ? ILugarTipo.Edificio : ILugarTipo.Parking
    const dependenciaTipo = lugar.tipo === ILugarTipo.Edificio ? ILugarTipo.Aula : ILugarTipo.Estacionamiento

    void axios.post(`http://localhost:5282/${lugarTipo}/${lugar.nombre}/${dependenciaTipo}`, {
      [`${dependenciaTipo}Name`]: dependenciaName
    }).then(() => onCreate?.())
  }

  return (
    <form onSubmit={handleCreate}>
      <div>
        <span>Crear {firstUpper(LugarDependencia[lugar.tipo]!.slice(0, -1))}</span>
      </div>
      <div className="inputs">
        <div className="container-input">
          <input required ref={inputRef} className='input' type="text" placeholder={'Nombre del ' + firstUpper(LugarDependencia[lugar.tipo]!.slice(0, -1))}/>
        </div>
      </div>
      <button className="send" type="submit">Crear {firstUpper(LugarDependencia[lugar.tipo]!.slice(0, -1))}</button>
    </form>
  )
}
