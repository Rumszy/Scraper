import java.awt.Color
import javax.swing.*

fun main() {
    gui(800, 200)
}

fun gui(width: Int, height: Int) {

    val gui = JFrame("Scraper")
    val guiPanel = JPanel()
    val hintText = JLabel("Insert your URL here:")
    val todoOne = JLabel("TODO1:")
    val todoTwo = JLabel("TODO2:")
    val todoThree = JLabel("TODO3:")
    val todoBoxOne = JCheckBox()
    val todoBoxTwo = JCheckBox()
    val todoBoxThree = JCheckBox()
    val urlText = JTextField()
    val scrapeButton = JButton("Scrape!")
    val exportButton = JButton("Export!")

    guiPanel.layout = null
    guiPanel.setSize(width, height)
    guiPanel.background = Color.gray

    hintText.setBounds(30, 30, 130, 30)
    hintText.isVisible = true

    urlText.setBounds(160, 30, 480, 30)
    urlText.isRequestFocusEnabled = true
    urlText.isVisible = true

    scrapeButton.setBounds(660, 30, 100, 30)
    scrapeButton.isVisible = true

    todoOne.setBounds(30, 100, 100, 30)
    todoOne.isVisible = true

    todoBoxOne.setBounds(130,105, 20, 20)
    todoBoxOne.background = Color.gray
    todoBoxOne.isVisible = true

    todoTwo.setBounds(180, 100, 100, 30)
    todoTwo.isVisible = true

    todoBoxTwo.setBounds(280,105, 20, 20)
    todoBoxTwo.background = Color.gray
    todoBoxTwo.isVisible = true

    todoThree.setBounds(330, 100, 100, 30)
    todoThree.isVisible = true

    todoBoxThree.setBounds(430,105, 20, 20)
    todoBoxThree.background = Color.gray
    todoBoxThree.isVisible = true

    exportButton.setBounds(660, 100, 100, 30)
    exportButton.isVisible = true

    guiPanel.add(hintText)
    guiPanel.add(urlText)
    guiPanel.add(scrapeButton)
    guiPanel.add(todoOne)
    guiPanel.add(todoBoxOne)
    guiPanel.add(todoTwo)
    guiPanel.add(todoBoxTwo)
    guiPanel.add(todoThree)
    guiPanel.add(todoBoxThree)
    guiPanel.add(exportButton)

    gui.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    gui.setSize(width, height)
    gui.isResizable = false
    gui.setLocationRelativeTo(null)
    gui.isVisible = true

    gui.add(guiPanel)
}