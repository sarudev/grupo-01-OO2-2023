import { useEffect } from 'react'
import { getBuildingDataset } from '../utils/utils'

export default function useBuildingNameSelector () {
  useEffect(() => {
    const lugaresTexto = document.querySelectorAll('.svg-lugar-text')

    function mouseenter (e: Event) {
      const { dataset } = getBuildingDataset(e)
      const building = document.querySelector(`.svg-lugar[data-lugar-num="${dataset.lugarNum}"]`) as SVGGElement
      building.classList.add('selected')
    }

    function mouseleave (e: Event) {
      const { dataset } = getBuildingDataset(e)
      const building = document.querySelector(`.svg-lugar[data-lugar-num="${dataset.lugarNum}"]`) as SVGGElement
      building.classList.remove('selected')
    }

    for (const text of lugaresTexto) {
      text.addEventListener('mouseenter', mouseenter)
      text.addEventListener('mouseleave', mouseleave)
    }

    return () => {
      for (const text of lugaresTexto) {
        text.removeEventListener('mouseenter', mouseenter)
        text.removeEventListener('mouseleave', mouseleave)
      }
    }
  }, [])
}
