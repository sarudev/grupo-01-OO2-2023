import { useEffect } from 'react'
import { getBuildingDataset } from '../utils/utils'

export default function useBuildingSelector () {
  useEffect(() => {
    const svgs = document.querySelectorAll('.svg-lugar')
    const svgtext = (buildingNumber: string) => document.querySelector(`.svg-lugar-text[data-lugar-num="${buildingNumber}"]>text`)

    function mouseEnter (e: Event) {
      const { dataset } = getBuildingDataset(e)
      if (dataset.lugarNombre === '') return

      const text = svgtext(dataset.lugarNum)!

      text.classList.add('selected')
    }

    function mouseLeave (e: Event) {
      const { dataset } = getBuildingDataset(e)
      if (dataset.lugarNombre === '') return

      const text = svgtext(dataset.lugarNum)!

      text.classList.remove('selected')
    }

    for (const svg of svgs) {
      svg.addEventListener('mouseenter', mouseEnter)
      svg.addEventListener('mouseleave', mouseLeave)
    }

    return () => {
      for (const svg of svgs) {
        svg.removeEventListener('mouseenter', mouseEnter)
        svg.removeEventListener('mouseleave', mouseLeave)
      }
    }
  }, [])
}
