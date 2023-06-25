import { firstUpper } from '../utils/utils'

export default function MouseCartelito ({ text, lugar }: { text: string, lugar: string }) {
  return (
    <div id='cartelitowo'>
      <div className="lugar">{firstUpper(lugar)}:</div>
      <div className="texto">{text}</div>
    </div>
  )
}
