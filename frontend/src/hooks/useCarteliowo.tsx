import { useEffect } from 'react'
import { getSvgCampusDataset } from '../utils/utils'

export default function useCartelitowo (cartelitoId: string, setCurrentBuilding: React.Dispatch<React.SetStateAction<string>>) {
  useEffect(() => {
    const svgs = document.querySelectorAll('.building')
    const cartelitowo = document.querySelector(`#${cartelitoId}`) as HTMLDivElement

    function mouseEnter (e: Event) {
      const { dataset } = getSvgCampusDataset(e)
      if (dataset.buildingName === 'N/A') return

      setCurrentBuilding(dataset.buildingName)
      cartelitowo.classList.add('visible')
    }

    function mouseLeave (e: Event) {
      const { dataset } = getSvgCampusDataset(e)
      if (dataset.buildingName === 'N/A') return

      cartelitowo.classList.remove('visible')
    }

    function mousemove (event: Event) {
      const e = event as MouseEvent

      const { dataset } = getSvgCampusDataset(e)
      if (dataset.buildingName === 'N/A') return

      const { pageX: x, pageY: y } = e

      cartelitowo.style.top = `${y}px`
      cartelitowo.style.left = `${x}px`
    }

    for (const svg of svgs) {
      svg.addEventListener('mouseenter', mouseEnter)
      svg.addEventListener('mouseleave', mouseLeave)
      svg.addEventListener('mousemove', mousemove)
    }

    return () => {
      for (const svg of svgs) {
        svg.removeEventListener('mouseenter', mouseEnter)
        svg.removeEventListener('mouseleave', mouseLeave)
        svg.removeEventListener('mousemove', mousemove)
      }
    }
  }, [])
}
