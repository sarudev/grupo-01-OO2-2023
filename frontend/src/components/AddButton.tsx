import { firstUpper } from '../utils/utils'
import '../styles/addbutton.scss'
import { useDispatch } from 'react-redux'
import { openModal } from '../redux/reducer/modal'

export default function AddButton ({ text }: { text: string }) {
  const dispatch = useDispatch()

  return (
    <button onClick={() => dispatch(openModal())} className='add'>
      <span className='mas'>+</span>
      <span className='text'>Añadir {firstUpper(text)}</span>
    </button>
  )
}
