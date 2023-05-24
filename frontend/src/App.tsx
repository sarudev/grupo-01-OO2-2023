import { useState } from 'react'
import './App.scss'
import { ReactComponent as Campus } from './assets/campus.svg'
import useBuildingSelector from './hooks/useBuildingSelector'
import useBuildingNameSelector from './hooks/useBuildingNameSelector'

function App () {
  const [currentBuilding, setCurrentBuilding] = useState('')
  useBuildingSelector('cartelitowo', setCurrentBuilding)
  useBuildingNameSelector()

  return (
    <div style={{ width: '100%', height: '100%' }}>
      <MouseCartelito text={currentBuilding} />
      <Campus />
    </div>
  )
}

function MouseCartelito ({ text }: { text: string }) {
  return (
    <div style={{ position: 'absolute', backgroundColor: '#242424', color: 'white', fontSize: '20px', whiteSpace: 'nowrap' }} id='cartelitowo'>
      {text}
    </div>
  )
}

export default App
