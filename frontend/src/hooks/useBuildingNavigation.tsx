import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

export default function useBuildingNavigation () {
  const navigate = useNavigate()

  useEffect(() => {
    const buildings = document.querySelectorAll('.building')

    function click (e: Event) {
      const elem = e.currentTarget as SVGGElement
      const dataset = elem.dataset as { buildingNumber: string }
      console.log(`/edificio/${dataset.buildingNumber}`)
      navigate(`/edificio/${dataset.buildingNumber}`)
    }

    for (const building of buildings) {
      building.addEventListener('click', click)
    }

    return () => {
      for (const building of buildings) {
        building.removeEventListener('click', click)
      }
    }
  }, [])
  return (
    <div>useBuildingNavigation</div>
  )
}
