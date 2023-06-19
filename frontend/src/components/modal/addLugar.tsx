import { firstUpper } from '../../utils/utils'
import { useRef, useEffect } from 'react'
import { type Lugares } from '../../types/types'
import axios from 'axios'
import { ILugarTipo, Routes } from '../../types/enums'
import { useAppDispatch } from '../../hooks/Redux.'
import { setDependencias } from '../../redux/reducer/dependencias'
import { closeModal } from '../../redux/reducer/modal'
import { isEdificio, isParking } from '../../types/typeguards'
import useLugarDependencia from '../../hooks/useLugarDependencia'
import useErrorMessage from '../../hooks/useErrorMessage'
import '../../styles/modalcontent.scss'

export default function AddLugar ({ lugar }: { lugar: Lugares }) {
  const { withoutS: nombreDependencia } = useLugarDependencia(lugar.tipo)
  const { Message, setError } = useErrorMessage()
  const dispatch = useAppDispatch()
  const inputRef = useRef<HTMLInputElement>(null)

  useEffect(() => {
    inputRef.current!.focus()
  }, [])

  const handleCreate: React.FormEventHandler<HTMLFormElement> = (e) => {
    e.preventDefault()
    const form = e.target as HTMLFormElement
    const input = form[0] as HTMLInputElement
    const dependencyName = input.value

    const dependenciaTipo = lugar.tipo === ILugarTipo.Edificio ? ILugarTipo.Aula : ILugarTipo.Estacionamiento

    async function requests () {
      try {
        await axios.post(`${Routes.BaseUrl}/${lugar.tipo}/${lugar.nombre}/${dependenciaTipo}`, { dependencyName }, { withCredentials: true })

        const { data } = await axios.get(`${Routes.BaseUrl}/${lugar.tipo}/${lugar.nombre}`, { withCredentials: true }) as { data: Lugares }

        const dependencias = isEdificio(data) ? data.aulas : isParking(data) ? data.estacionamientos : null
        dispatch(setDependencias(dependencias))
        dispatch(closeModal())
      } catch (e: any) {
        console.log(e)
        setError(`Ya existe un ${lugar.tipo} con ese nombre. `)
      }
    }

    void requests()
  }

  return (
    <form onSubmit={handleCreate}>
      <div>
        <span>Crear {firstUpper(nombreDependencia!)}</span>
      </div>
      <div className="inputs">
        <div className="container-input">
          <input required ref={inputRef} className='input' type="text" placeholder={'Nombre del ' + firstUpper(nombreDependencia!)}/>
        </div>
      </div>
      <button className="send" type="submit">Crear {firstUpper(nombreDependencia!)}</button>
      <Message />
    </form>
  )
}
