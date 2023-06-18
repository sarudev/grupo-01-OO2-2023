import { useState } from 'react'
import { ReactComponent as Campus } from '../assets/campus.svg'
import useBuildingSelector from '../hooks/useBuildingSelector'
import useBuildingNameSelector from '../hooks/useBuildingNameSelector'
import useBuildingNavigation from '../hooks/useBuildingNavigation'
import useCartelitowo from '../hooks/useCarteliowo'
import MouseCartelito from '../components/Cartelitowo'
import '../styles/campus.scss'

export default function Index () {
  const [currentBuilding, setCurrentBuilding] = useState('')
  useBuildingSelector()
  useBuildingNameSelector()
  useCartelitowo('cartelitowo', setCurrentBuilding)

  useBuildingNavigation()

  return (
    <div className="campus-container">
      <MouseCartelito text={currentBuilding} />
      <Campus />
    </div>
  )
}
