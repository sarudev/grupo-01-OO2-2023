import { useCallback, useState } from 'react'
import { firstUpper } from '../utils/utils'

interface ITab {
  handleTabSelect: (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void
  currentTab: string
  tabName: string
}

export default function Tabs ({ tabsNames }: { tabsNames: string[] }) {
  const [currentTab, setCurrentTab] = useState(tabsNames[0])

  function handleTabSelect (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
    setCurrentTab(e.currentTarget.dataset.tab!)
  }

  const Tab = useCallback(function Tab ({ handleTabSelect, currentTab, tabName }: ITab) {
    return <button onClick={handleTabSelect} data-tab={tabName} className={`tab ${tabName} ${currentTab === tabName ? 'selected' : ''}`}>{firstUpper(tabName)}</button>
  }, [currentTab])

  return (
    <>
      {tabsNames.map((e, i) => <Tab currentTab={currentTab} handleTabSelect={handleTabSelect} tabName={e} key={i} />)}
    </>
  )
}
