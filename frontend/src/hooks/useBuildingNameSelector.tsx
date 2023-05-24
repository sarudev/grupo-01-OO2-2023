import { useEffect } from 'react'

export default function useBuildingNameSelector () {
  useEffect(() => {
    const buildingNames = document.querySelectorAll('.building-name')

    function mouseenter (e: Event) {
      const elem = e.currentTarget as SVGPathElement
      const dataset = elem.dataset as { buildingNumber: string }
      const building = document.querySelector(`.building[data-building-number="${dataset.buildingNumber}"]`) as SVGGElement
      building.classList.add('selected')
    }

    function mouseleave (e: Event) {
      const elem = e.currentTarget as SVGPathElement
      const dataset = elem.dataset as { buildingNumber: string }
      const building = document.querySelector(`.building[data-building-number="${dataset.buildingNumber}"]`) as SVGGElement
      building.classList.remove('selected')
    }

    for (const text of buildingNames) {
      text.addEventListener('mouseenter', mouseenter)
      text.addEventListener('mouseleave', mouseleave)
    }

    return () => {
      for (const text of buildingNames) {
        text.removeEventListener('mouseenter', mouseenter)
        text.removeEventListener('mouseleave', mouseleave)
      }
    }
  }, [])
}
