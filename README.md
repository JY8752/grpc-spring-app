# grpc-spring-app
gRPC, spring, kotlinのテストアプリ
と思っていたけどいろいろ追加

## WebFlux
croutineだからWebfluxで作った方がいいのかと思ってwebfluxにした。
のでmongoもR2DBC

## Github Actions
どうせなのでCI/CD的なものを練習。
ktlintとテストが通ったらマージできるようにした。
デプロイは別途CodePipelineで作ってみようと思う。
submoduleからprotoのコード自動生成失敗してめっちゃはまった。
