package com.micronautics.publish

import buildInfo.BuildInfo
import java.io.File

/** Utility for creating combined Scaladoc for an SBT multi-project.
  * Must be run from top-level git repo directory */
object Publish extends App {
  val config: Config = Config.default

  implicit val project: Project = Project(
    gitHubName = "mslinn",
    name       = BuildInfo.name,
    version    = BuildInfo.version,
    copyright  = "Copyright 2017 Micronautics Research Corporation. All rights reserved."
  )

  // subprojects to document; others are ignored (such as this one)
  val subprojects: List[SubProject] =
    List("root", "demo")
      .map(x => new SubProject(x, new File(x).getAbsoluteFile))

  new Documenter(config, subprojects).publish()
}
