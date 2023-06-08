import { firstUpper } from '../utils/utils'
import '../styles/addbutton.scss'

export default function AddButton ({ text, onClick }: { text: string, onClick: () => void }) {
  return (
    <button onClick={onClick} className='add'>
      <span className='mas'>+</span>
      <span className='text'>Añadir {firstUpper(text)}</span>
    </button>
  )
}
