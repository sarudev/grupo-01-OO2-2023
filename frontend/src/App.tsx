import { useEffect, useState, useRef } from 'react'
import './App.scss'
import { ReactComponent as Campus } from './assets/campus.svg'

function App () {
  const [currentEdificio, setCurrentEdificio] = useState('')

  useEffect(() => {
    const svgs = document.querySelectorAll('.edificio-campus')
    const cartelitowo = document.querySelector('#cartelitowo') as HTMLDivElement

    function mouseEnter (e: Event) {
      const elem = e.target as SVGGElement
      const id = elem.id
      let arr = id.split('-')
      ;[arr[0], arr[1]] = ['', '']
      arr = arr.filter(e => e.length > 0)

      setCurrentEdificio(arr.join(' '))
      cartelitowo?.classList.add('visible')

      // const background = document.querySelector(`#${id.replace('edificio', 'background').toLowerCase()}`)!

      // ;(background as SVGRectElement | SVGPathElement).style.setProperty('stroke', 'cornflowerblue')
    }

    function mouseLeave () {
      cartelitowo?.classList.remove('visible')
    }

    function mousemove (event: Event) {
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
        svg.addEventListener('mouseleave', mouseLeave)
        svg.removeEventListener('mousemove', mousemove)
      }
    }
  })

  return (
    <div style={{ width: '100%', height: '100%' }}>
      <MouseCartelito text={currentEdificio} />
      <Campus />
    </div>
  )
}

function MouseCartelito ({ text }: { text: string }) {
  return (
    <div style={{ position: 'absolute', backgroundColor: '#242424', color: 'white', fontSize: '20px', whiteSpace: 'nowrap' }} id='cartelitowo'>
      {text}
    </div>
  )
}

export default App
