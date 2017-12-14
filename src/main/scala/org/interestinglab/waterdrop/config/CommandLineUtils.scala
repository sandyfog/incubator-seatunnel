package org.interestinglab.waterdrop.config

object CommandLineUtils {

  /**
   * command line arguments parser.
   * */
  val parser = new scopt.OptionParser[CommandLineArgs]("start-waterdrop.sh") {
    head("Waterdrop", "1.0.0")

    opt[String]('f', "file").required().action((x, c) => c.copy(configFile = x)).text("config file")
    opt[Unit]('t', "check").action((_, c) => c.copy(testConfig = true)).text("check config")
    opt[String]('m', "master")
      .required()
      .action((x, c) => c.copy(master = x))
      .validate(x => if (Common.isModeAllowed(x)) success else failure("master: " + x + " is not allowed."))
      .text("spark master")
  }
}
