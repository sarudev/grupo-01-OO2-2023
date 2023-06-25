import { useLayoutEffect, useState } from 'react'
import { lugarProperties } from '../../../types/enums'
import List from '../../List'
import { firstUpper } from '../../../utils/utils'
import { type Lugares } from '../../../types/types'

export default function Estado ({ lugar, visible }: { lugar: Lugares, visible: boolean }) {
  const [estados, setEstados] = useState<Array<Record<string, boolean | null>>>([])

  useLayoutEffect(() => {
    const keys = Object.keys(lugar)
    const values = Object.values(lugar) as Array<boolean | null>

    const states = []

    for (let i = 0; i < keys.length; i++) {
      if (!lugarProperties.includes(keys[i])) {
        states.push({ [keys[i]]: values[i] })
      }
    }

    setEstados(states)
  }, [])

  if (!visible) return null

  return (
    <List<Record<string, boolean | null>> array={estados} itemName='estado'>
      {(s) => {
        const key = Object.keys(s)[0] as string
        const value = Object.values(s)[0] as boolean | null

        return (
          <>
            <div className='state'>{firstUpper(key)}</div>
            <div className="info">
              <div className="info-message">{value == null ? 'N/A' : value ? 'Activo' : 'Desativado'}</div>
              <div className='activo' data-activo={value == null ? 'null' : value} />
            </div>
          </>
        )
      }}
    </List>
  )
}
