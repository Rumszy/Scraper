import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.awt.FlowLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.net.URL
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.imageio.ImageIO
import javax.swing.*


class Scraper {

    var jpgCounter: Int = 1
    var jpegCounter: Int = 1
    var pngCounter: Int = 1
    var gifCounter: Int = 1
    private lateinit var gui: JFrame
    private lateinit var guiPanel: JPanel
    private lateinit var hintText: JLabel
    private lateinit var browseText: JLabel
    private lateinit var emailTypes: JLabel
    private lateinit var comCheckBox: JCheckBox
    private lateinit var ukCheckBox: JCheckBox
    private lateinit var huCheckBox: JCheckBox
    private lateinit var orgCheckBox: JCheckBox
    private lateinit var netCheckBox: JCheckBox
    private lateinit var imageTypes: JLabel
    private lateinit var jpgCheckBox: JCheckBox
    private lateinit var jpegCheckBox: JCheckBox
    private lateinit var pngCheckBox: JCheckBox
    private lateinit var gifCheckBox: JCheckBox
    private lateinit var links: JLabel
    private lateinit var linksCheckBox: JCheckBox
    private lateinit var source: JLabel
    private lateinit var sourceCheckbox: JCheckBox
    private lateinit var tables: JLabel
    private var tablesSize = JTextField("0")
    private lateinit var rows: JLabel
    private var rowsSize = JTextField("0")
    private lateinit var columns: JLabel
    private var columnsSize = JTextField("0")
    private var urlText = JTextField("")
    private var locationText = JTextField("")
    val scrapeButton = JButton("Scrape!").apply {
        actionCommand = "S"
        addActionListener(ButtonClickListener())
    }

    init {
        prepareGUI()
    }

    private fun prepareGUI() {
        hintText = JLabel("Insert your URL here:", JLabel.CENTER)
        browseText = JLabel("Add the destination folder:", JLabel.CENTER)
        emailTypes = JLabel("Email Types:", JLabel.CENTER)
        comCheckBox = JCheckBox("com")
        ukCheckBox = JCheckBox("uk")
        huCheckBox = JCheckBox("hu")
        orgCheckBox = JCheckBox("org")
        netCheckBox = JCheckBox("net")
        imageTypes = JLabel("Image Types:", JLabel.CENTER)
        jpgCheckBox = JCheckBox("jpg")
        jpegCheckBox = JCheckBox("jpeg")
        pngCheckBox = JCheckBox("png")
        gifCheckBox = JCheckBox("gif")
        links = JLabel("Links:", JLabel.CENTER)
        linksCheckBox = JCheckBox("")
        source = JLabel("Source:", JLabel.CENTER)
        sourceCheckbox = JCheckBox("")
        tables = JLabel("How many Tables: ", JLabel.CENTER)
        rows = JLabel("How many Rows: ", JLabel.CENTER)
        columns = JLabel("How many Columns: ", JLabel.CENTER)

        guiPanel = JPanel().apply { layout = FlowLayout() }

        var gbl = GridBagLayout()
        var gbc = GridBagConstraints()

        gui = JFrame("Scraper").apply {
            setSize(1400, 250)
            layout = gbl
            addWindowListener(object : WindowAdapter() {
                override fun windowClosing(windowEvent: WindowEvent?) {
                    System.exit(0)
                }
            })

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 0
            gbc.gridy = 0
            add(hintText ,gbc)

            gbc.weightx = 4.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 1
            gbc.gridy = 0
            gbc.gridwidth = 4
            add(urlText ,gbc)

            gbc.weightx = 4.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 5
            gbc.gridy = 0
            gbc.gridwidth = 1
            add(scrapeButton ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 0
            gbc.gridy = 1
            add(browseText ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 1
            gbc.gridy = 1
            gbc.gridwidth = 4
            add(locationText ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 0
            gbc.gridy = 2
            gbc.gridwidth = 1
            add(emailTypes ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 1
            gbc.gridy = 2
            add(comCheckBox ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 2
            gbc.gridy = 2
            add(ukCheckBox ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 3
            gbc.gridy = 2
            add(huCheckBox ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 4
            gbc.gridy = 2
            add(orgCheckBox ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 5
            gbc.gridy = 2
            add(netCheckBox ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 0
            gbc.gridy = 3
            add(imageTypes ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 1
            gbc.gridy = 3
            add(jpgCheckBox ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 2
            gbc.gridy = 3
            add(jpegCheckBox ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 3
            gbc.gridy = 3
            add(pngCheckBox ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 4
            gbc.gridy = 3
            add(gifCheckBox ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 0
            gbc.gridy = 4
            add(links ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 1
            gbc.gridy = 4
            add(linksCheckBox ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 0
            gbc.gridy = 5
            add(source ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 1
            gbc.gridy = 5
            add(sourceCheckbox ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 0
            gbc.gridy = 6
            add(tables ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 1
            gbc.gridy = 6
            add(tablesSize ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 2
            gbc.gridy = 6
            add(rows ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 3
            gbc.gridy = 6
            add(rowsSize ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 4
            gbc.gridy = 6
            add(columns ,gbc)

            gbc.weightx = 1.0
            gbc.fill = GridBagConstraints.HORIZONTAL
            gbc.gridx = 5
            gbc.gridy = 6
            add(columnsSize ,gbc)

            isVisible = true
        }
    }

    private inner class ButtonClickListener : ActionListener {
        override fun actionPerformed(e: ActionEvent) {

            val text: String = urlText.text
            if (urlText.text.isNotBlank()) {
                when (e.actionCommand) {

                    "S" -> {

                        val res: Connection.Response = Jsoup.connect(text)
                            .ignoreContentType(true)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .referrer("http://www.google.com")
                            .timeout(12000)
                            .followRedirects(true)
                            .maxBodySize(0)
                            .timeout(0)
                            .execute()

                        var dest: String = locationText.text.replace("\\", "\\\\")

                        val doc: Document = res.parse()

                        /*---------------------------------------------Email---------------------------------------------*/

                        if (comCheckBox.isSelected || ukCheckBox.isSelected || huCheckBox.isSelected ||
                            orgCheckBox.isSelected || netCheckBox.isSelected) {
                            try {

                                //Emailek tárolása
                                var emails = emptyList<String>()
                                val pEmail: Pattern = Pattern.compile(
                                    "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",
                                    Pattern.CASE_INSENSITIVE
                                )
                                val mEmail: Matcher = pEmail.matcher(doc.text())

                                while (mEmail.find()) {
                                    emails += mEmail.group()
                                }

                                //Emailek filterezése
                                var filteredEmails = emptyList<String>()

                                for (it in emails) {

                                    var atIndex: Int = it.indexOf("@")

                                    if (((it.endsWith(".com", true) && comCheckBox.isSelected) ||
                                        (it.endsWith(".uk", true) && ukCheckBox.isSelected) ||
                                        (it.endsWith(".hu", true) && huCheckBox.isSelected) ||
                                        (it.endsWith(".org", true) && orgCheckBox.isSelected) ||
                                        (it.endsWith(".net", true) && netCheckBox.isSelected)) &&
                                        !filteredEmails.contains(it) && it.substring(0, atIndex).length <= 64 &&
                                                !it.contains("..")
                                    ) {

                                        filteredEmails += it
                                    }
                                }
                                println(filteredEmails)

                                //Emailek kiírása
                                if (filteredEmails.isNotEmpty()) {
                                    val file = FileWriter("$dest\\Emails.txt")
                                    for (it in filteredEmails) {
                                        file.write(it + System.lineSeparator())
                                    }

                                    file.close()
                                }

                            } catch (e: Exception) {
                                println("EMAIL HIBA")
                            }
                        }

                        /*---------------------------------------------Email---------------------------------------------*/

                        /*---------------------------------------------Link---------------------------------------------*/

                        if (linksCheckBox.isSelected) {
                            try {

                                //Linkek tárolása
                                val links: Elements = doc.select("a[href]")

                                //Linkek filterezése
                                var filteredLinks = emptyList<String>()

                                for (it in links) {
                                    if ((!filteredLinks.contains(it.attr("href").toString())) &&
                                        it.attr("href").toString().length > 4 &&
                                        it.attr("href").toString().startsWith("http")
                                    ) {
                                        filteredLinks += it.attr("href").toString()
                                    }
                                }

                                //Linkek kiírása
                                val file = FileWriter("$dest\\Links.txt")

                                for (it in filteredLinks) {
                                    file.write(it)
                                    file.write(System.lineSeparator())
                                }

                                file.close()

                            } catch (e: IOException) {
                                println("LINK HIBA")
                            }
                        }

                        /*---------------------------------------------Link---------------------------------------------*/

                        /*---------------------------------------------Forrás---------------------------------------------*/

                        if (sourceCheckbox.isSelected) {
                            File("$dest\\Source.txt").writeText(doc.toString())
                        }

                        /*---------------------------------------------Forrás---------------------------------------------*/

                        /*---------------------------------------------Kép---------------------------------------------*/

                        if (jpgCheckBox.isSelected || jpegCheckBox.isSelected || pngCheckBox.isSelected || gifCheckBox.isSelected) {
                            //Képek tárolása
                            try {

                                val imageLinks: Elements = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]")

                                //Képek filterezése
                                var filteredImageLinks = emptyList<String>()

                                for (it in imageLinks) {
                                    if ((it.attr("src").toString().startsWith("h") &&
                                                !filteredImageLinks.contains(it.attr("src").toString())) &&
                                        ((it.attr("src").toString().contains("jpg", true) && jpgCheckBox.isSelected) ||
                                                (it.attr("src").toString().contains("jpeg", true) && jpegCheckBox.isSelected) ||
                                                (it.attr("src").toString().contains("png", true) && pngCheckBox.isSelected)||
                                                (it.attr("src").toString().contains("gif", true) && gifCheckBox.isSelected))
                                    ) {
                                        filteredImageLinks += it.attr("src").toString()
                                    }
                                }

                                //Képek kiírása
                                for (it in filteredImageLinks) {

                                    var urlImage = URL(it)
                                    var image: BufferedImage = ImageIO.read(urlImage)

                                    when {
                                        it.contains("jpg", true) -> {
                                            var outPutFile = File("$dest\\JPGs\\$jpgCounter.jpg")
                                            outPutFile.parentFile.mkdirs()
                                            ImageIO.write(image, "jpg", outPutFile)
                                            jpgCounter++
                                        }
                                        it.contains("jpeg", true) -> {
                                            var outPutFile = File("$dest\\JPEGs\\$jpegCounter.jpeg")
                                            outPutFile.parentFile.mkdirs()
                                            ImageIO.write(image, "jpeg", outPutFile)
                                            jpegCounter++
                                        }
                                        it.contains("png", true) -> {
                                            var outPutFile = File("$dest\\PNGs\\$pngCounter.png")
                                            outPutFile.parentFile.mkdirs()
                                            ImageIO.write(image, "png", outPutFile)
                                            pngCounter++
                                        }
                                        it.contains("gif", true) -> {
                                            var outPutFile = File("$dest\\GIFs\\$gifCounter.gif")
                                            outPutFile.parentFile.mkdirs()
                                            ImageIO.write(image, "gif", outPutFile)
                                            gifCounter++
                                        }
                                    }
                                }
                                jpgCounter = 1
                                jpegCounter = 1
                                pngCounter = 1
                                gifCounter = 1

                            } catch (e: Exception) {
                                println("KÉP HIBA")
                            }
                        }

                        /*---------------------------------------------Kép---------------------------------------------*/

                        /*---------------------------------------------Táblázat---------------------------------------------*/

                        if (tablesSize.text.matches("\\d+".toRegex()) && rowsSize.text.matches("\\d+".toRegex()) &&
                            columnsSize.text.matches("\\d+".toRegex()) && tablesSize.text.toInt() > 0 &&
                            rowsSize.text.toInt() > 0 && columnsSize.text.toInt() > 0) {
                            try {

                                var x: Int = 1 //Táblázat
                                var megadottTablazat = tablesSize.text.toInt()
                                var megadottSor = rowsSize.text.toInt()
                                var megadottOszlop = columnsSize.text.toInt()

                                var file2 = FileWriter("$dest\\Table$x.txt")

                                        var tables: Elements = doc.select("table")

                                for (table in tables) {

                                    var y: Int = 0 //Sor

                                    var rows: Elements = table.select("tr")

                                    for (row in rows) {

                                        var z: Int = 0 //Oszlop

                                        var columns: Elements = row.select("td, th")

                                        for (col in columns) {

                                            if (z < megadottOszlop) { //z < columns.size - az összes oszlop esetén // z < megadottOszlop
                                                file2.write(col.text().toString())
                                                file2.write(" ")
                                                z += 1
                                            } else {
                                                break
                                            }
                                        }

                                        if (y < megadottSor - 1) { //y < rows.size - az összes sor esetén // y < megadottSor - 1
                                            file2.write(System.lineSeparator())
                                            y += 1
                                        } else {
                                            break
                                        }
                                    }

                                    file2.close()

                                    if (megadottTablazat > tables.size) {
                                        megadottTablazat = tables.size
                                    }

                                    if (x < megadottTablazat) { //x < tables.size - összes tábla esetén
                                        x += 1
                                        file2 = FileWriter("$dest\\Table$x.txt")
                                    } else {
                                        break
                                    }
                                    println(rows.size)
                                }

                                file2.close()

                            } catch (e: Exception) {
                                println("TÁBLÁZAT HIBA")
                            }
                        } else {
                            tablesSize.text = "0"
                            rowsSize.text = "0"
                            columnsSize.text = "0"
                        }

                        /*---------------------------------------------Táblázat---------------------------------------------*/

                        /*---------------------------------------------Teszt---------------------------------------------*/

                        //println(doc)
                        //println(emails)
                        //println(filteredEmails)
                        //println(phoneNumbers)
                        //println(filteredImageURLs)

                        /*---------------------------------------------Teszt---------------------------------------------*/
                    }
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    var scraper = Scraper()
}