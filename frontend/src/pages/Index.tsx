import { useState } from 'react'
import { ReactComponent as Campus } from '../assets/campus.svg'
import useBuildingSelector from '../hooks/useBuildingSelector'
import useBuildingNameSelector from '../hooks/useBuildingNameSelector'
import useBuildingNavigation from '../hooks/useBuildingNavigation'
import useCartelitowo from '../hooks/useCarteliowo'
import MouseCartelito from '../components/Cartelitowo'

export default function Index () {
  const [currentBuilding, setCurrentBuilding] = useState('')
  useBuildingSelector()
  useBuildingNameSelector()
  useCartelitowo('cartelitowo', setCurrentBuilding)

  useBuildingNavigation()

  return (
    <div style={{ width: '100%', height: '100%' }}>
      <MouseCartelito text={currentBuilding} />
      <Campus />
    </div>
  )
}
