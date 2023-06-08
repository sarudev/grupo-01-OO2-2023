import { type ReactElement } from 'react'
import { createPortal } from 'react-dom'
import '../styles/modal.scss'

const modalElem = document.querySelector('#modal')!

export default function Modal ({ children, open, closeModal }: { children: ReactElement, open: boolean, closeModal: () => void }) {
  if (!open) return null

  return createPortal(
    <>
      <div className="background" onClick={closeModal} />
      <div className='modal'>
        <button className="close" onClick={closeModal}>x</button>
        <div className="content">{children}</div>
      </div>
    </>,
    modalElem
  )
}
