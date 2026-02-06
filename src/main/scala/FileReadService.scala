object FileReadService {
  def readPath(filePath: String): String = {
    //CWE-22
    //SINK
    os.read(os.Path(filePath))
  }
}
