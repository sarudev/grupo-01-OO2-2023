import { type ReactElement } from 'react'
import { createPortal } from 'react-dom'
import '../styles/modal.scss'

const modalElem = document.querySelector('#modal')!

export default function Modal ({ children, open, closeModal }: { children: ReactElement, open: boolean, closeModal: () => void }) {
  if (!open) return null

  return createPortal(
    <>
      <div className='close-message'>Haz click fuera del recuadro para cerrarlo</div>
      <div className="background" onClick={closeModal} />
      {children}
    </>,
    modalElem
  )
}
