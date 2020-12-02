const { PlaintextToHtmlConverter } = require("./plaintext_to_html_converter.js")
const fs = require("fs")
const notifier = require('./notifier.js')
jest.mock("fs")
jest.mock("./notifier.js")

describe("Text Converter", () => {
  it("simple word", () => {
    fs.readFileSync.mockReturnValue("simple")
    const converter = new PlaintextToHtmlConverter()
    const result = converter.toHtml()
    expect(result).toEqual("simple")
    expect(notifier.notify).toHaveBeenCalledWith('HTML encoding done.')
  })
})
