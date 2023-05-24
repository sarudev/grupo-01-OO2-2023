import { useEffect } from 'react'

export default function useBuildingSelector (cartelitoId: string, setCurrentBuilding: React.Dispatch<React.SetStateAction<string>>) {
  useEffect(() => {
    const svgs = document.querySelectorAll('.building')
    const cartelitowo = document.querySelector(`#${cartelitoId}`) as HTMLDivElement
    const svgtext = (buildingNumber: string) => document.querySelector(`.building-name[data-building-number="${buildingNumber}"]>.text`)

    function mouseEnter (e: Event) {
      const elem = e.target as SVGGElement
      const id = elem.id
      let arr = id.split('-')
      ;[arr[0], arr[1]] = ['', '']
      arr = arr.filter(e => e.length > 0)
      const dataset = elem.dataset as { buildingName: string, buildingNumber: string }
      if (dataset.buildingName === 'N/A') return

      const text = svgtext(dataset.buildingNumber)!

      setCurrentBuilding(dataset.buildingName)
      cartelitowo.classList.add('visible')
      text.classList.add('selected')
    }

    function mouseLeave (e: Event) {
      const elem = e.target as SVGGElement
      const dataset = elem.dataset as { buildingName: string, buildingNumber: string }
      if (dataset.buildingName === 'N/A') return

      const text = svgtext(dataset.buildingNumber)!

      cartelitowo.classList.remove('visible')
      text.classList.remove('selected')
    }

    function mousemove (event: Event) {
      const elem = event.currentTarget as SVGRectElement
      const dataset = elem.dataset as { buildingName: string }
      if (dataset.buildingName === 'N/A') return

      const e = event as MouseEvent
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
