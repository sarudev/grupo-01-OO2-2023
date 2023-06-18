import { type ReactElement } from 'react'
import { createPortal } from 'react-dom'
import '../styles/modal.scss'
import { useDispatch } from 'react-redux'
import { closeModal } from '../redux/reducer/modal'
import { useAppSelector } from '../hooks/Redux.'

const modalElem = document.querySelector('#modal')!

export default function Modal ({ children }: { children: ReactElement }) {
  const dispatch = useDispatch()
  const open = useAppSelector(s => s.modal)

  if (!open) return null

  return createPortal(
    <>
      <div className='close-message'>Haz click fuera del recuadro para cerrarlo</div>
      <div className="background" onClick={() => dispatch(closeModal())} />
      {children}
    </>,
    modalElem
  )
}
