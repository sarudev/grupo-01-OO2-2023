import { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

export default function useBuildingNavigation () {
  const navigate = useNavigate()

  useEffect(() => {
    const buildings = document.querySelectorAll('.building')
    const texts = document.querySelectorAll('.building-name')

    function getId (e: Event) {
      const elem = e.currentTarget as SVGGElement
      const dataset = elem.dataset as { buildingNumber: string, buildingName: string }
      return `/edificio/${dataset.buildingName.replaceAll(' ', '-')}`
    }

    function click (e: Event) {
      navigate(getId(e))
    }

    // function auxclick (e: Event) {
    //   window.open(getId(e))
    // }

    for (const building of buildings) {
      building.addEventListener('click', click)
      // building.addEventListener('auxclick', auxclick)
    }

    for (const text of texts) {
      text.addEventListener('click', click)
      // text.addEventListener('auxclick', auxclick)
    }

    return () => {
      for (const building of buildings) {
        building.removeEventListener('click', click)
        // building.removeEventListener('auxclick', auxclick)
      }

      for (const text of texts) {
        text.removeEventListener('click', click)
        // text.removeEventListener('auxclick', auxclick)
      }
    }
  }, [])
}
