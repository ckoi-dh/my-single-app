/**
 * Local storage utilities
 */

export const StorageKeys = {
  TOKEN: 'token',
  USER_INFO: 'userInfo'
}

export const storage = {
  // Set item with optional expiration
  setItem(key: string, value: any, expire?: number): void {
    const item = {
      value,
      timestamp: Date.now(),
      expire: expire ? Date.now() + expire * 1000 : null
    }
    localStorage.setItem(key, JSON.stringify(item))
  },

  // Get item
  getItem(key: string): any {
    const item = localStorage.getItem(key)
    if (!item) return null

    try {
      const parsed = JSON.parse(item)

      // Check if item has expired
      if (parsed.expire && Date.now() > parsed.expire) {
        localStorage.removeItem(key)
        return null
      }

      return parsed.value
    } catch {
      localStorage.removeItem(key)
      return null
    }
  },

  // Remove item
  removeItem(key: string): void {
    localStorage.removeItem(key)
  },

  // Clear all items
  clear(): void {
    localStorage.clear()
  }
}

export const getToken = (): string | null => {
  return storage.getItem(StorageKeys.TOKEN)
}

export const setToken = (token: string, expire?: number): void => {
  storage.setItem(StorageKeys.TOKEN, token, expire)
}

export const removeToken = (): void => {
  storage.removeItem(StorageKeys.TOKEN)
}

export const getUserInfo = () => {
  return storage.getItem(StorageKeys.USER_INFO)
}

export const setUserInfo = (userInfo: any, expire?: number): void => {
  storage.setItem(StorageKeys.USER_INFO, userInfo, expire)
}

export const removeUserInfo = (): void => {
  storage.removeItem(StorageKeys.USER_INFO)
}
