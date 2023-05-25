import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { getSvgCampusDataset } from '../utils/utils'

export default function useBuildingNavigation () {
  const navigate = useNavigate()

  useEffect(() => {
    const buildings = document.querySelectorAll('.building')
    const texts = document.querySelectorAll('.building-name')

    function click (e: Event) {
      const { dataset } = getSvgCampusDataset(e)
      navigate(`/edificio/${dataset.buildingName.replaceAll(' ', '-')}`)
    }

    for (const building of buildings) {
      building.addEventListener('click', click)
    }

    for (const text of texts) {
      text.addEventListener('click', click)
    }

    return () => {
      for (const building of buildings) {
        building.removeEventListener('click', click)
      }

      for (const text of texts) {
        text.removeEventListener('click', click)
      }
    }
  }, [])
}
