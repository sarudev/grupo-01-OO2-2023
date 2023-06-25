import { useLayoutEffect } from 'react'
import { getBuildingDataset } from '../utils/utils'

export default function useCartelitowo (cartelitoId: string, setCurrentBuilding: React.Dispatch<React.SetStateAction<{ type: string, name: string }>>) {
  useLayoutEffect(() => {
    const svgs = document.querySelectorAll('.svg-lugar')
    const cartelitowo = document.querySelector(`#${cartelitoId}`) as HTMLDivElement

    function mouseEnter (e: Event) {
      const { dataset } = getBuildingDataset(e)
      if (dataset.lugarNombre === '') return

      setCurrentBuilding({ type: dataset.lugarTipo, name: dataset.lugarNombre })
      cartelitowo.classList.add('visible')
    }

    function mouseLeave (e: Event) {
      const { dataset } = getBuildingDataset(e)
      if (dataset.lugarNombre === '') return

      cartelitowo.classList.remove('visible')
    }

    function mousemove (event: Event) {
      const e = event as MouseEvent

      const { dataset } = getBuildingDataset(e)
      if (dataset.lugarNombre === '') return

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
