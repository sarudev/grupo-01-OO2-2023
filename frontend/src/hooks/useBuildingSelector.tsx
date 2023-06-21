import { useEffect } from 'react'
import { getBuildingDataset } from '../utils/utils'

export default function useBuildingSelector () {
  useEffect(() => {
    const svgs = document.querySelectorAll('.building')
    const svgtext = (buildingNumber: string) => document.querySelector(`.building-name[data-building-number="${buildingNumber}"]>.text`)

    function mouseEnter (e: Event) {
      const { dataset } = getBuildingDataset(e)
      if (dataset.buildingName === 'N/A') return

      const text = svgtext(dataset.buildingNumber)!

      text.classList.add('selected')
    }

    function mouseLeave (e: Event) {
      const { dataset } = getBuildingDataset(e)
      if (dataset.buildingName === 'N/A') return

      const text = svgtext(dataset.buildingNumber)!

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
