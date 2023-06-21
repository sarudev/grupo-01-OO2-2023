import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { getBuildingDataset } from '../utils/utils'

export default function useBuildingNavigation () {
  const navigate = useNavigate()

  useEffect(() => {
    const buildings = document.querySelectorAll('.svg-lugar')
    const texts = document.querySelectorAll('.svg-lugar-text')

    function click (e: Event) {
      const { dataset } = getBuildingDataset(e)
      if (dataset.lugarNombre === '') return

      navigate(`/${dataset.lugarTipo.replace(' ', '').replace('v', 'V')}/${dataset.lugarNombre.replaceAll(' ', '-')}`)
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
