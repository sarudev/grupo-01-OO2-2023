export function getBuildingDataset (e: Event) {
  const elem = e.currentTarget as SVGGElement
  const dataset = elem.dataset as { buildingName: string, buildingNumber: string }

  return {
    elem,
    dataset
  }
}

export function strToUrl (str: string) {
  return str.replaceAll(' ', '-')
}

export function urlToStr (str: string) {
  return str.replaceAll('-', ' ')
}

export function firstUpper (str: string) {
  return str.replace(str[0], str[0].toUpperCase())
}
