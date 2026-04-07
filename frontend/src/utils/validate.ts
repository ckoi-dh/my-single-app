/**
 * Validation utilities
 */

export const isEmail = (email: string): boolean => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return emailRegex.test(email)
}

export const isUsername = (username: string): boolean => {
  const usernameRegex = /^[a-zA-Z0-9_\u4e00-\u9fa5]{2,20}$/
  return usernameRegex.test(username)
}

export const isPassword = (password: string): boolean => {
  return password.length >= 6 && password.length <= 20
}

export const isUrl = (url: string): boolean => {
  const urlRegex = /^(https?:\/\/)?([\da-z.-]+)\.([a-z.]{2,6})([/\w .-]*)*\/?$/
  return urlRegex.test(url)
}
