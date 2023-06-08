import { firstUpper } from '../../utils/utils'
import { useRef, useEffect } from 'react'
import '../../styles/modalcontent.scss'

export default function AddLugar ({ dependencia, onCreate }: { dependencia: string, onCreate: React.FormEventHandler<HTMLFormElement> }) {
  const inputRef = useRef<HTMLInputElement>(null)

  useEffect(() => {
    inputRef.current!.focus()
  }, [])

  return (
    <form onSubmit={onCreate}>
      <div>
        <span>Crear {firstUpper(dependencia)}</span>
      </div>
      <div className="inputs">
        <div className="container-input">
          <input ref={inputRef} className='input' type="text" placeholder={'Nombre del ' + firstUpper(dependencia)}/>
        </div>
      </div>
      <button className="send" type="submit">Crear {firstUpper(dependencia)}</button>
    </form>
  )
}
