import { useCallback, useLayoutEffect } from 'react'
import { firstUpper } from '../../../utils/utils'
import { useAppSelector } from '../../../hooks/Redux.'
import { useDispatch } from 'react-redux'
import { setTab } from '../../../redux/reducer/currentTab'

interface ITab {
  currentTab: string
  tabName: string
}

export default function Tabs ({ tabsNames }: { tabsNames: Array<string | null> }) {
  const currentTab = useAppSelector(s => s.currentTab)
  const dispatch = useDispatch()

  useLayoutEffect(() => {
    for (const tab of tabsNames) {
      if (tab != null) {
        dispatch(setTab(tab))
        break
      }
    }
  }, [])

  const Tab = useCallback(function Tab ({ currentTab, tabName }: ITab) {
    return <button onClick={(e) => dispatch(setTab(e.currentTarget.dataset.tab!))} data-tab={tabName} className={`tab ${tabName} ${currentTab === tabName ? 'selected' : ''}`}>{firstUpper(tabName)}</button>
  }, [currentTab])

  return (
    <>
      {tabsNames.map((e, i) => e != null ? <Tab currentTab={currentTab} tabName={e} key={i} /> : null)}
    </>
  )
}
